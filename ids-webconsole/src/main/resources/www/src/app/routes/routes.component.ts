import { Component, EventEmitter, OnDestroy, OnInit, Output } from '@angular/core';
import { BrowserModule, Title } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { IntervalObservable } from 'rxjs/observable/IntervalObservable';
import { Observable } from 'rxjs/Observable';
import { Subscription } from 'rxjs/Subscription';

import { Route } from './route';
import { RouteService } from './route.service';
import { RouteMetrics } from './route-metrics';

@Component({
    selector: 'route-list',
    templateUrl: './routes.component.html',
    styleUrls: ['./routes.component.css']
})

export class RoutesComponent implements OnInit, OnDestroy {
    title = 'Current Routes';
    @Output() changeTitle = new EventEmitter();
    routes: Array<Route>;
    selectedRoute: Route;
    routemetrics: RouteMetrics = new RouteMetrics();

    private alive: boolean;

    constructor(private titleService: Title, private routeService: RouteService) {
        this.titleService.setTitle('Message Routes');

        this.routeService.getRoutes()
            .subscribe(routes => {
                this.routes = routes;
            });
    }

    ngOnInit(): void {
        this.changeTitle.emit('Camel Routes');
        // Update route metrics every second
        IntervalObservable.create(1000)
            .takeWhile(() => this.alive)
            .mergeMap(() => this.routeService.getMetrics())
            .subscribe(routeMetrics => this.routemetrics = routeMetrics);
        this.alive = true;
    }

    ngOnDestroy(): void {
        this.alive = false;
    }

    trackRoutes(index: number, item: Route): string {
        return item.id;
    }

    onSelect(route: Route): void {
        this.selectedRoute = route;
    }
}

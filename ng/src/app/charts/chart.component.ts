import { NgxChartsModule } from '@swimlane/ngx-charts';
import { ActivatedRoute, Router } from '@angular/router';
import { Component } from '@angular/core';

@Component({
    template: `
    <ngx-charts-line-chart
        [view]="view"
        [scheme]="colorScheme"
        [results]="multi"
        [gradient]="gradient"
        [xAxis]="showXAxis"
        [yAxis]="showYAxis"
        [legend]="showLegend"
        [showXAxisLabel]="showXAxisLabel"
        [showYAxisLabel]="showYAxisLabel"
        [xAxisLabel]="xAxisLabel"
        [yAxisLabel]="yAxisLabel"
        [autoScale]="autoScale"
        [timeline]="timeline"
        (select)="onSelect($event)">
    </ngx-charts-line-chart>`
})
export class ChartComponent{
    
    constructor(private router:Router,
        activatedRoute:ActivatedRoute){

            const id = activatedRoute.snapshot.params['id'];
            
        }
}
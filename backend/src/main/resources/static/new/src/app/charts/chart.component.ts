import { ActivatedRoute, Router } from '@angular/router';
import { Component } from '@angular/core';
import { LoginService } from '../user/login.service';
import { ChartService, Chart } from './chart.service';

@Component({
    selector: `chart-component`,
    templateUrl: `chart.component.html`
})
export class ChartComponent {

    chart: Chart;

    view: any[];

    // options
    barPadding = 150;
    showXAxis = true;
    showYAxis = true;
    gradient = false;
    showLegend = false;
    showXAxisLabel = true;
    xAxisLabel = 'Topics';
    showYAxisLabel = true;
    showDataLabel = true;
    yAxisLabel = 'Quotes';
    colorScheme = {
        domain: ['#5AA454', '#A10A28', '#C7B42C', '#AAAAAA']
      };

    constructor(private router: Router,
        activatedRoute: ActivatedRoute, public loginService: LoginService, private service: ChartService) {
            this.service.getChart().subscribe(
                chart => this.chart = chart,
                error => console.log(error)
            );
            Object.assign(this, this.chart);
            console.log(this.chart);
    }


    onSelect(event) {
        console.log(event);
      }
}
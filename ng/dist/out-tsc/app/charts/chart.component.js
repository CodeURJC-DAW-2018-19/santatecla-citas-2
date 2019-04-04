import * as tslib_1 from "tslib";
import { ActivatedRoute, Router } from '@angular/router';
import { Component } from '@angular/core';
var ChartComponent = /** @class */ (function () {
    function ChartComponent(router, activatedRoute) {
        this.router = router;
        var id = activatedRoute.snapshot.params['id'];
    }
    ChartComponent = tslib_1.__decorate([
        Component({
            template: "\n    <ngx-charts-line-chart\n        [view]=\"view\"\n        [scheme]=\"colorScheme\"\n        [results]=\"multi\"\n        [gradient]=\"gradient\"\n        [xAxis]=\"showXAxis\"\n        [yAxis]=\"showYAxis\"\n        [legend]=\"showLegend\"\n        [showXAxisLabel]=\"showXAxisLabel\"\n        [showYAxisLabel]=\"showYAxisLabel\"\n        [xAxisLabel]=\"xAxisLabel\"\n        [yAxisLabel]=\"yAxisLabel\"\n        [autoScale]=\"autoScale\"\n        [timeline]=\"timeline\"\n        (select)=\"onSelect($event)\">\n    </ngx-charts-line-chart>"
        }),
        tslib_1.__metadata("design:paramtypes", [Router,
            ActivatedRoute])
    ], ChartComponent);
    return ChartComponent;
}());
export { ChartComponent };
//# sourceMappingURL=chart.component.js.map
import {Component, OnInit} from '@angular/core';

@Component({
    selector: 'app-home',
    templateUrl: './pickhelper.component.html',
    styleUrls: ['./pickhelper.component.scss']
})

export class PickHelperComponent implements OnInit {
    model = {
        left: true,
        middle: false,
        right: false
    };

    constructor() {
    }

    ngOnInit() {
    }
}

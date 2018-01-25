import {Component, OnInit} from '@angular/core';

@Component({
    selector: 'app-home',
    templateUrl: './index.component.html',
    styleUrls: ['./index.component.scss']
})

export class IndexComponent implements OnInit {
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

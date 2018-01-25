import { Component, OnInit, Inject, Renderer, ElementRef, OnDestroy } from '@angular/core';
import { DataService } from '../../service/data.service';

@Component({
    selector: 'app-ability-top',
    templateUrl: './ability-top.component.html',
    styleUrls: ['./ability-top.component.scss']
})

/**
 * 技能排行榜
 */
export class AbilityTopComponent implements OnInit, OnDestroy {

    constructor( private dataService:DataService) {}

    _dataSet = [];

    ngOnInit() {
        this.dataService.getHerosTop().subscribe(
            res => {
                console.log(res);
                //const data = JSON.parse(res);
                this._dataSet = res;
                console.log(this._dataSet);
            },
            error => {


            });
        // const body = document.getElementsByTagName('app-nucleoicons')[0];
        let navbar = document.getElementsByTagName('app-navbar')[0].children[0];
        //navbar.classList.add('navbar-hidden');
        // body.classList.add('demo-icons');
    }

    ngOnDestroy(){

    }
}

import {Component, OnInit, Inject, Renderer, ElementRef, OnDestroy} from '@angular/core';
import {DataService} from '../../service/data.service';

@Component({
    selector: 'app-choose-hero',
    templateUrl: './choose-img.component.html',
    styleUrls: ['./choose-img.component.scss']
})
export class ChooseImgComponent implements OnInit, OnDestroy {

    constructor(private dataService: DataService) {
    }

    _dataSet = [];

    ngOnInit() {
        this.dataService.getAllHeroes().subscribe(
            res => {
                console.log(res);
                //const data = JSON.parse(res);
                this._dataSet = res.heroes;
                console.log(this._dataSet);
            },
            error => {


            });
        // const body = document.getElementsByTagName('app-nucleoicons')[0];
        let navbar = document.getElementsByTagName('app-navbar')[0].children[0];
        //navbar.classList.add('navbar-hidden');
        // body.classList.add('demo-icons');
    }

    ngOnDestroy() {
        let navbar = document.getElementsByTagName('app-navbar')[0].children[0];
        //navbar.classList.remove('navbar-hidden');
    }
}

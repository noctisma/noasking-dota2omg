import {Component, OnInit, Inject, Renderer, ElementRef, OnDestroy} from '@angular/core';
import {DataService} from '../service/data.service';

@Component({
    selector: 'app-hero',
    templateUrl: './hero.component.html',
    styleUrls: ['./hero.component.scss']
})
export class HeroComponent implements OnInit, OnDestroy {

    loadingIndicator: boolean = true;
    reorderable: boolean = true;

    columns = [
        { prop: 'name' },
        { name: 'Gender' },
        { name: 'Company', sortable: false }
    ];

    rows = [
        { name: 'Austin', gender: 'Male', company: 'Swimlane' },
        { name: 'Dany', gender: 'Male', company: 'KFC' },
        { name: 'Molly', gender: 'Female', company: 'Burger King' },
    ];

    

    constructor(private dataService: DataService) {
        // this.fetch((data) => {
        //     this.rows = data;
        //     setTimeout(() => { this.loadingIndicator = false; }, 1500);
        // });
    }



    ngOnInit() {
        // const body = document.getElementsByTagName('app-nucleoicons')[0];
        let navbar = document.getElementsByTagName('app-navbar')[0].children[0];
        //navbar.classList.add('navbar-hidden');
        // body.classList.add('demo-icons');
    }

    ngOnDestroy() {
        let navbar = document.getElementsByTagName('app-navbar')[0].children[0];
        //navbar.classList.remove('navbar-hidden');
    }

    fetch(cb) {
        const req = new XMLHttpRequest();
        req.open('GET', `assets/data/company.json`);

        req.onload = () => {
            cb(JSON.parse(req.response));
        };

        req.send();
    }
}

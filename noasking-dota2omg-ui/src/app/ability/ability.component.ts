import {Component, OnInit, Inject, Renderer, ElementRef, OnDestroy,ViewChild} from '@angular/core';
import {DataService} from '../service/data.service';
import {DatatableComponent} from '@swimlane/ngx-datatable';

@Component({
    selector: 'app-ability',
    templateUrl: './ability.component.html',
    styleUrls: ['./ability.component.scss']
})
export class AbilityComponent implements OnInit, OnDestroy {

    loadingIndicator: boolean = true;
    reorderable: boolean = true;

    columns = [
        { prop: 'name' },
        { name: 'Gender' },
        { name: 'Company' }
    ];

    dropdownValue: string = "7天";

    rows = [];
    temp = [];
    @ViewChild(DatatableComponent) table: DatatableComponent;

    constructor(private dataService: DataService) {
        console.log('init');
        this.fetch((data) => {
            this.temp = [...data];
            this.rows = data;
            console.log(this.rows);
            setTimeout(() => { this.loadingIndicator = false; }, 1500);
        });
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

    updateFilter(event) {
        const val = event.target.value.toLowerCase();

        // 过滤数据
        const temp = this.temp.filter(function(d) {
            return d.name.toLowerCase().indexOf(val) !== -1 || !val;
        });

        // update the rows
        this.rows = temp;
        // Whenever the filter changes, always go back to the first page
        this.table.offset = 0;
    }

    /**
     * 下拉框重新选择刷新数据
     * @param val
     */
    rebuild(val:string) {
        this.dropdownValue = val;
    }

}

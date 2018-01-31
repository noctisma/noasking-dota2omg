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

    rows = [];
    temp = [];
    @ViewChild(DatatableComponent) table: DatatableComponent;

    constructor(private dataService: DataService) {
        // console.log('init');
        // this.fetch((data) => {
        //     this.temp = [...data];
        //     this.rows = data;
        //     console.log(this.rows);
        //     setTimeout(() => { this.loadingIndicator = false; }, 1500);
        // });
    }

    public getData(timeType:string){
        this.dataService.getAbilityTopList(timeType).subscribe(
            (data)=>{
                this.rows=data;
                this.temp = [...data];
                this.table.offset = 0;
            }
        );
    }

    ngOnInit() {
        this.getData('All');

    }

    ngOnDestroy() {
        let navbar = document.getElementsByTagName('app-navbar')[0].children[0];
        //navbar.classList.remove('navbar-hidden');
    }

    // fetch(cb) {
    //     const req = new XMLHttpRequest();
    //     req.open('GET', `assets/data/company.json`);
    //
    //     req.onload = () => {
    //         cb(JSON.parse(req.response));
    //     };
    //
    //     req.send();
    // }

    updateFilter(event) {
        const val = event.target.value.toLowerCase();
        const temp = this.temp.filter(function(d) {
            return d.localizedName.toLowerCase().indexOf(val) !== -1 || !val;
        });
        this.rows = temp;
        this.table.offset = 0;

    }

    dropdownValue:string="7天";

    /**
     * 下拉框重新选择刷新数据
     * @param val
     */
    rebuild(val:string,name:string) {
        this.dropdownValue = name;
        this.getData(val);
    }

}

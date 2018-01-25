import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {RouterModule} from '@angular/router';

import {AbilityComponent} from './ability.component';
import {DataService} from '../service/data.service';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';



@NgModule({
    imports: [
        CommonModule,
        BrowserModule,
        FormsModule,
        RouterModule,
        NgbModule.forRoot(),
        NgxDatatableModule
    ],
    declarations: [AbilityComponent],
    exports: [AbilityComponent],
    providers: [DataService]
})
export class AbilityModule {
}
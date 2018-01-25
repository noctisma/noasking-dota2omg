import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {RouterModule} from '@angular/router';

import {IndexComponent} from './index.component';
import {AbilityTopComponent} from './ability-top/ability-top.component';
import { DataService } from '../service/data.service';


@NgModule({
    imports: [
        CommonModule,
        BrowserModule,
        FormsModule,
        RouterModule,
        NgbModule.forRoot(),
    ],
    declarations: [IndexComponent,AbilityTopComponent],
    exports: [IndexComponent],
    providers: [DataService]
})
export class IndexModule {
}

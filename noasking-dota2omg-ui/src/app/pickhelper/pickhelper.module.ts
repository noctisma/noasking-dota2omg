import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {RouterModule} from '@angular/router';

import {PickHelperComponent} from './pickhelper.component';
import {ChooseAbilityComponent} from './choose-ability/choose-ability.component';
import {DataService} from '../service/data.service';
import {ChooseImgComponent} from "./choose-img/choose-img.component";


@NgModule({
    imports: [
        CommonModule,
        BrowserModule,
        FormsModule,
        RouterModule,
        NgbModule.forRoot(),
    ],
    declarations: [PickHelperComponent, ChooseAbilityComponent, ChooseImgComponent],
    exports: [PickHelperComponent],
    providers: [DataService]
})
export class PickHelperModule {
}

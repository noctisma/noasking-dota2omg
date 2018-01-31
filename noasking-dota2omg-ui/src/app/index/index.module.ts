import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {RouterModule} from '@angular/router';

import {IndexComponent} from './index.component';
import { DataService } from '../service/data.service';
import { FileUploadModule } from 'ng2-file-upload';


@NgModule({
    imports: [
        CommonModule,
        BrowserModule,
        FormsModule,
        RouterModule,
        NgbModule.forRoot(),
        FileUploadModule
    ],
    declarations: [IndexComponent],
    exports: [IndexComponent],
    providers: [DataService]
})
export class IndexModule {
}

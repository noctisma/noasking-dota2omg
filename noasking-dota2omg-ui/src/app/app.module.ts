import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {RouterModule} from '@angular/router';
import {AppRoutingModule} from './app.routing';

import {AppComponent} from './app.component';
import {NavbarComponent} from './shared/navbar/navbar.component';
import {FooterComponent} from './shared/footer/footer.component';
import {HttpModule} from "@angular/http";

import {IndexModule} from './index/index.module';
import {HeroModule} from "./hero/hero.module";
import {PickHelperModule} from "./pickhelper/pickhelper.module";
import {AbilityModule} from "./ability/ability.module";


@NgModule({
    declarations: [
        AppComponent,
        // ProfileComponent,
        NavbarComponent,
        FooterComponent
    ],
    imports: [
        BrowserModule,
        NgbModule.forRoot(),
        FormsModule,
        HttpModule,
        RouterModule,
        AppRoutingModule,
        IndexModule,
        HeroModule,
        AbilityModule,
        PickHelperModule
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}

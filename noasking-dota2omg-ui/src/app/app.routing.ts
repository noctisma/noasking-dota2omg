import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {BrowserModule} from '@angular/platform-browser';
import {Routes, RouterModule} from '@angular/router';

import {IndexComponent} from './index/index.component';
import {HeroComponent} from "./hero/hero.component";
import {ChooseImgComponent} from "./pickhelper/choose-img/choose-img.component";
import {ChooseAbilityComponent} from "./pickhelper/choose-ability/choose-ability.component";
import {AbilityComponent} from "./ability/ability.component";
const routes: Routes = [
    {path: 'index', component: IndexComponent},
    {path: 'hero', component: HeroComponent},
    {path: 'ability', component: AbilityComponent},
    {path: 'chooseimg', component: ChooseImgComponent},
    {path: 'chooseability', component: ChooseAbilityComponent},
    {path: '', redirectTo: 'index', pathMatch: 'full'}
];

@NgModule({
    imports: [
        CommonModule,
        BrowserModule,
        RouterModule.forRoot(routes)
    ],
    exports: [],
})
export class AppRoutingModule {
}

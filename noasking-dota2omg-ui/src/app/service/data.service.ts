import {Injectable} from "@angular/core";
import {Http, Headers, Response, RequestOptions, RequestMethod, Request} from "@angular/http";
import "rxjs/add/operator/map";

@Injectable()
export class DataService {
    constructor(private http: Http) {
    }

    /**
     * 获取目录列表
     * @returns {Int8Array|Response[]|Int16Array|Int32Array|Uint32Array|Uint16Array|any}
     */
    getAllHeroes() {
        return this.http.get("assets/data/heroes.json").map((response: Response) => response.json());
    }

    getHerosTop() {
        return this.http.get("assets/data/heroes-top.json").map((response: Response) => response.json());
    }

}

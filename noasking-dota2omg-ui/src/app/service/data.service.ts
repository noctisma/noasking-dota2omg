import {Injectable} from "@angular/core";
import {Http, Headers, Response, RequestOptions, RequestMethod, Request} from "@angular/http";
import "rxjs/add/operator/map";

@Injectable()
export class DataService {
    constructor(private http: Http) {
    }

    /**
     * 获取英雄排行榜
     * @param timeType
     * @returns {Int8Array|Response[]|Int16Array|Int32Array|Uint32Array|Uint16Array|any}
     */
    getHeroTopList(timeType: string) {
        return this.http.get("api/getHeroTopList?timeType=" + timeType).map((response: Response) => response.json());
    }

    /**
     * 获取技能排行榜
     * @param timeType
     * @returns {Uint8Array|Float64Array|Int32Array|Uint32Array|any[]|Uint16Array|any}
     */
    getAbilityTopList(timeType: string) {
        return this.http.get("api/getAbilityTopList?timeType=" + timeType).map((response: Response) => response.json());
    }

    /**
     * 获取所以英雄信息
     * @returns {Uint8Array|Float64Array|Int32Array|Uint32Array|any[]|Uint16Array|any}
     */
    getAllHero() {
        return this.http.get("api/getAllHero").map((response: Response) => response.json());
    }

    /**
     * 获取所有技能信息
     * @returns {Uint8Array|Float64Array|Int32Array|Uint32Array|any[]|Uint16Array|any}
     */
    getAllAbility() {
        return this.http.get("api/getAllAbility").map((response: Response) => response.json());
    }


}

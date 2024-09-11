import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, Observable, of } from 'rxjs';
import { Material } from '../interfaces/material';

@Injectable({
  providedIn: 'root'
})
export class MaterialsService {

  private apiURL: string = 'http://localhost:8080/api'

  constructor( private http: HttpClient) { }


  searchCity(query:string):Observable<Material[]>{
    const getAllByCity: string = `${this.apiURL}/material/porCiudad/${query}`;
    return this.apiQueryGET(getAllByCity);
  }

  getAll():Observable<Material[]>{
    const getAll: string = `${this.apiURL}/material`;
    return this.apiQueryGET(getAll);
  }

  searchByTypeAndPurchaseDate(query:string, date:string):Observable<Material[]>{
    const getAllByTypeAndDate: string = `${this.apiURL}/material/porTipoYfecha?type=${query}&purchaseDate=${date}`;
    return this.apiQueryGET(getAllByTypeAndDate);
  }


  apiQueryGET(query:string):Observable<Material[]>{
    return this.http.get<Material[]>( query )
    .pipe(
      catchError(() => of([]))
    );
  };

  // searchMaterialByAlphaCode(code: string): Observable<Material | null>{
  //   const byAlphaCode: string = `${this.apiURL}/alpha/${code}`;
  //   return this.http.get<Material[]>( byAlphaCode )
  //   .pipe(
  //     map( countries => countries.length > 0 ? countries[0]: null),
  //     catchError(() => of(null))
  //   );
  // }

  // searchCapital(query:string): Observable<Material[]> {
  //   const byCapitalQueryURL: string = `${this.apiURL}/capital/${query}`;
  //   return this.apiQueryGET(byCapitalQueryURL);
  // }

  // searchRegion(query:string): Observable<Material[]> {
  //   const byregionQueryURL: string = `${this.apiURL}/region/${query}`;
  //   return this.apiQueryGET(byregionQueryURL);
  // }

  // searchMaterial(query:string): Observable<Material[]> {
  //   const byMaterialQueryURL: string = `${this.apiURL}/name/${query}`;
  //   return this.apiQueryGET(byMaterialQueryURL);
  // }
}

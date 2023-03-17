import { Injectable } from '@angular/core';
import BASE_URL from './helper';
import {HttpClient} from '@angular/common/http'
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient:HttpClient) { }

  public signup(user:User){
    return this.httpClient.post(`${BASE_URL}/users/`,user)
  }
}

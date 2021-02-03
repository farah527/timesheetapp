
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { IUsers } from './iusers';
import { GenericApiService } from 'src/app/common/shared';

@Injectable({
  providedIn: 'root'
})
export class UsersService extends GenericApiService<IUsers> { 
  constructor(protected httpclient: HttpClient) { 
    super(httpclient, "users");
  }
  
  
  
}

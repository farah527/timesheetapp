
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UsersService } from 'src/app/entities/users/users.service';
@Injectable({
  providedIn: 'root'
})
export class UsersExtendedService extends UsersService {
	constructor(protected httpclient: HttpClient) { 
    super(httpclient);
    this.url += '/extended';
  }
}

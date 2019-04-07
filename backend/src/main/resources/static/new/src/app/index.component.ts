import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from './user/login.service';
@Component({
    templateUrl: './index.component.html'
})
export class IndexComponent {
    constructor(private router: Router,public loginService: LoginService) {

    }

}

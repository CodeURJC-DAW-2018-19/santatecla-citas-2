import { Component, ViewChild, TemplateRef } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService, User } from './login.service';
import { MatDialog, MatDialogRef } from '@angular/material';
import { UserService } from './user.service';

@Component({
    selector: 'register',
    templateUrl: './register.component.html',
})
export class RegisterComponent {
    event: any;
    user: string;
    password: string;
    @ViewChild('registerDialog') registerDialog: TemplateRef<any>;
    dialogRef: MatDialogRef<any, any>;

    constructor(public dialog: MatDialog, private router: Router, public loginService: LoginService) {}

    register() {
        event.preventDefault();
        let usuario:User = {userName:this.user,roles:["ROLE_USER"],password:this.password};
                this.loginService.register(usuario).subscribe(
            (u) => {
                console.log(u);
                this.dialogRef.close();
            },
            (error) => alert('Invalid user or password'),
        );
    }


    openRegisterDialog() {
        this.dialogRef = this.dialog.open(this.registerDialog, {
            width: '50%',
            height: '50%',
        });
    }
}
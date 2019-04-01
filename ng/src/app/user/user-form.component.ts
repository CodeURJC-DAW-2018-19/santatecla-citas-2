import { UserLog } from './login.service';
import {Component} from '@angular/core';
import {Router,ActivatedRoute} from '@angular/router';
import { User,UserService} from './user.service';

@Component({
    templateUrl: 'user-form.component.html'
})
export class UserFormComponent{
    newUser: boolean;
    user: User;

    constructor(private router:Router,
        activatedRoute:ActivatedRoute,
        private service: UserService){

            const id = activatedRoute.snapshot.params['id'];
            if(id){
                service.getuser(id).subscribe(
                    user => this.user = user,
                    error => console.error(error)
                );
                this.newUser = false;

            }else{
                this.user= {id:0,email:" ",passwar:""};
                this.newUser = true;
            }
        }

    cancel(){
        window.history.back();
    }

    save(){
        this.service.addUser(this.user).subscribe(
            User=>{},
            error=> console.error('Error creating user: '+ error),
        );
        window.history.back();
    }
}
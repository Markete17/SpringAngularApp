import { Component,OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})

export class SignupComponent implements OnInit{

  constructor(private userService:UserService){}

  public user : User = new User()

  ngOnInit(): void {
    
  }

  formSubmit(){
    console.log(this.user)
    if(this.user.username == '' || this.user.username == null){
      alert('nombre usuario requerido')
      return
    }

    this.userService.signup(this.user).subscribe(
      (response) => {
        console.log(response)
      },
      (error) =>{
        console.log("system error")
      }
    )
  }

}

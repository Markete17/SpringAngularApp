import { Component,OnInit } from '@angular/core';
import { User } from 'src/app/models/User';
import { UserService } from 'src/app/services/user.service';
import { of } from 'rxjs';
import { MatSnackBar } from '@angular/material/snack-bar';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})

export class SignupComponent implements OnInit{

  constructor(private userService:UserService, private snack:MatSnackBar){}

  public user : User = new User()

  ngOnInit(): void {
    
  }

  formSubmit(){
    console.log(this.user)
    if(this.user.username == '' || this.user.username == null){
      this.openSnack("Username is required.")
      return
    }
    of(this.userService.signup(this.user)).subscribe({
      next: (response) => Swal.fire('Completed!', `Welcome ${this.user.firstName}!`,'success'),
      error: (e)=>this.openSnack("System error"),
      complete: () => console.info('complete')
    });
  }

  openSnack(message:string){
    this.snack.open(message,'OK',{
      duration : 3000,
      verticalPosition: 'top',
      horizontalPosition: 'right'
    })
  }

}

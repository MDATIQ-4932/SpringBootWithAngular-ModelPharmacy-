import { Component, OnInit } from "@angular/core";
import { UserModule } from "../module/user/user.module";
import { AuthService } from "../service/auth.service";
import { faHome, faUser, faSignInAlt, faSignOutAlt, faUserPlus, faSearch, faAppleAlt, faEye, faPlus, faShoppingCart, faChartLine, } from '@fortawesome/free-solid-svg-icons';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})



export class NavbarComponent implements OnInit {


  username: string | null = null;
  role: string | null = null;

  // navbar.component.ts
  isLoggedIn = false;



  userRole: string | null = '';
  currentUser: UserModule | null = null;

  // Declare FontAwesome icons
  faHome = faHome;
  faUser = faUser;
  faSignInAlt = faSignInAlt;
  faSignOutAlt = faSignOutAlt;
  faUserPlus = faUserPlus;
  faSearch = faSearch;
  faAppleAlt = faAppleAlt;
  faEye = faEye;
  faPlus = faPlus;
  faShoppingCart = faShoppingCart;
  faChartLine = faChartLine;

  constructor(protected authService: AuthService) { }





  ngOnInit(): void {
    this.isLoggedIn = this.authService.isLoggedIn();

    this.authService.currentUser$.subscribe(user => {
      this.currentUser = user;
    });

    this.username = this.authService.getUsername();

    console.log(this.username)
    this.role = this.authService.getUserRole();
    console.log(this.role);
  }
}
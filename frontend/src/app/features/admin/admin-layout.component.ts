import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import {
  BarChart3,
  Calendar,
  DollarSign,
  LayoutDashboard,
  LogOut,
  LucideAngularModule,
  Plus,
  Search,
  Settings,
  Sparkles,
  Stethoscope,
  UserCog,
  Users
} from 'lucide-angular';

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [RouterOutlet, RouterLink, RouterLinkActive, LucideAngularModule],
  templateUrl: './admin-layout.component.html'
})
export class AdminLayoutComponent {
  readonly Sparkles = Sparkles;
  readonly LayoutDashboard = LayoutDashboard;
  readonly Users = Users;
  readonly Calendar = Calendar;
  readonly Stethoscope = Stethoscope;
  readonly DollarSign = DollarSign;
  readonly UserCog = UserCog;
  readonly BarChart3 = BarChart3;
  readonly Settings = Settings;
  readonly LogOut = LogOut;
  readonly Search = Search;
  readonly Plus = Plus;
}
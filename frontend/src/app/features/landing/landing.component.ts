import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import {
  CalendarCheck,
  CheckCircle,
  Menu,
  ShieldCheck,
  Smile,
  Sparkles,
  Stethoscope,
  UserCheck,
  LucideAngularModule
} from 'lucide-angular';

@Component({
  selector: 'app-landing',
  standalone: true,
  imports: [RouterLink, LucideAngularModule],
  templateUrl: './landing.component.html'
})
export class LandingComponent {
  readonly Stethoscope = Stethoscope;
  readonly Sparkles = Sparkles;
  readonly UserCheck = UserCheck;
  readonly CalendarCheck = CalendarCheck;
  readonly Smile = Smile;
  readonly ShieldCheck = ShieldCheck;
  readonly CheckCircle = CheckCircle;
  readonly Menu = Menu;
}
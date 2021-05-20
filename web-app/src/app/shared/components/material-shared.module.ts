import { NgModule } from '@angular/core';
import {MatCardModule} from '@angular/material/card';
import {MatButtonModule} from '@angular/material/button';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatIconModule} from '@angular/material/icon';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {MatInputModule} from '@angular/material/input';
import {ReactiveFormsModule} from '@angular/forms';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {CommonModule} from '@angular/common';
import {MatMenuModule} from '@angular/material/menu';
import {MatDividerModule} from '@angular/material/divider';
import { MatDialogModule} from '@angular/material/dialog';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatSelectModule} from '@angular/material/select';

@NgModule({
  imports: [
    MatSnackBarModule,
    MatCardModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatFormFieldModule,
    MatTooltipModule,
    MatProgressBarModule,
    MatInputModule,
    ReactiveFormsModule,
    MatProgressSpinnerModule,
    MatMenuModule,
    MatDialogModule,
    MatDividerModule,
    MatCheckboxModule,
    MatExpansionModule,
    MatSelectModule,
    CommonModule
  ],
  exports: [
    MatSnackBarModule,
    MatCardModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatFormFieldModule,
    MatTooltipModule,
    MatProgressBarModule,
    MatProgressSpinnerModule,
    MatInputModule,
    MatMenuModule,
    MatDialogModule,
    MatDividerModule,
    MatCheckboxModule,
    MatExpansionModule,
    MatSelectModule,
    ReactiveFormsModule
  ],
  declarations: []
})
export class MaterialSharedModule { }

import { Component, Inject, OnInit } from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogModule, MatDialogRef} from '@angular/material/dialog';
import {MatButtonModule} from '@angular/material/button';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormBuilder, FormGroup, FormsModule, Validators, ReactiveFormsModule} from '@angular/forms';
import { ConsumeServeService } from '../services/consume-serve.service';

export interface DialogDataCategory {
  name: string;
  color: string;
}

@Component({
  selector: 'app-dialog-category',
  standalone: true,
  imports: [MatDialogModule, MatButtonModule,
    FormsModule, 
    ReactiveFormsModule,
    MatFormFieldModule, 
    MatInputModule,
    MatCheckboxModule],
  templateUrl: './dialog-category.component.html',
  styleUrl: './dialog-category.component.scss'
})
export class DialogCategoryComponent {
  public formGroup: FormGroup;

  constructor(private fb: FormBuilder, private serve: ConsumeServeService,
    public dialogRef: MatDialogRef<DialogCategoryComponent>,
    @Inject(MAT_DIALOG_DATA) public dialogData: DialogDataCategory){
    this.formGroup = this.fb.group({
      name: [dialogData?.name, [Validators.required]],
      color: [dialogData?.color, [Validators.required]],
    });

}

save(){ 
    if(this.formGroup.valid){
      this.dialogRef.close(this.formGroup.value);
    }
  }
}

import { Component, Inject, OnInit } from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogModule, MatDialogRef} from '@angular/material/dialog';
import {MatButtonModule} from '@angular/material/button';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormBuilder, FormGroup, FormsModule, Validators, ReactiveFormsModule} from '@angular/forms';
import { ConsumeServeService } from '../../services/consume-serve.service';


export interface DialogData {
  title: string;
  description: string;
  categoryId: number;
}

@Component({
  selector: 'app-dialog-note',
  standalone: true,
  imports: [MatDialogModule, MatButtonModule,
    FormsModule, 
    ReactiveFormsModule,
    MatFormFieldModule, 
    MatInputModule,
    MatCheckboxModule],
  templateUrl: './dialog-note.component.html',
  styleUrl: './dialog-note.component.scss'
})
export class DialogNoteComponent implements OnInit {
  public formGroup: FormGroup;
  public categories: Array<any>;

  constructor(private fb: FormBuilder, private serve: ConsumeServeService,
    public dialogRef: MatDialogRef<DialogNoteComponent>,
    @Inject(MAT_DIALOG_DATA) public dialogData: DialogData){
    this.formGroup = this.fb.group({
      title: [dialogData.title, [Validators.required]],
      description: [dialogData.description, [Validators.required]],
      categoryId: [dialogData.categoryId, [Validators.required]]
    });
    this.categories = [];
  }
  
    ngOnInit(): void {
    this.serve.get("/category?nroPage=0&regXPage=30&order=id").subscribe(
      (res: any) => {
        console.log(res);
        this.categories = res.data;
      },
      (err: any) => {
        console.log(err);
      }
    );
  }

  save(){
    this.dialogRef.close(this.formGroup.value);
  }

}

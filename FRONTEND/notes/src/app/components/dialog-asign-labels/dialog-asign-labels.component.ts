import { Component, Inject, OnInit } from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogModule, MatDialogRef} from '@angular/material/dialog';
import {MatButtonModule} from '@angular/material/button';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule} from '@angular/forms';
import { ConsumeServeService } from '../../services/consume-serve.service';
import { Category } from '../../interfaces/category.interface';


export interface DialogData {
  id: number;
  title: string;
  description: string;
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
  templateUrl: './dialog-asign-labels.component.html',
  styleUrl: './dialog-asign-labels.component.scss'
})
export class DialogAsignLabelsComponent implements OnInit {
  public formGroup: FormGroup;
  public categories: Array<Category>;

  constructor(private fb: FormBuilder, private serve: ConsumeServeService,
    public dialogRef: MatDialogRef<DialogAsignLabelsComponent>,
    @Inject(MAT_DIALOG_DATA) public dialogData: DialogData){
    this.formGroup = this.fb.group({
      id: [dialogData?.id]
    });
    this.categories = [];
  }
  
    ngOnInit(): void {
    this.serve.get("/categories?noteId=" + this.formGroup.get('id')?.value).subscribe(
      (res: any) => {
        this.categories = res.data;
      },
      (err: any) => {
        console.log(err);
      }
    );
  }

  update(completed: boolean, index: number) {
    this.categories![index].asigned = completed ? 1 : 0;
  }

  save(){ 
    if(this.formGroup.valid){
      this.dialogRef.close(this.categories);
    }
  }

}

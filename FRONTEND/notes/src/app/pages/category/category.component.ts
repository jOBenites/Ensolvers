import { RouterModule } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import Category from "../../modules/category.model";
import { ConsumeServeService } from "../../services/consume-serve.service";

import { MatCardModule } from '@angular/material/card';
import { MatListModule } from '@angular/material/list';
import {MatIconModule} from '@angular/material/icon';
import { DialogCategoryComponent } from '../../dialog-category/dialog-category.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-category',
  standalone: true,
   imports: [
     RouterModule,
     MatCardModule,
    MatListModule,
    MatIconModule
   ],
  templateUrl: './category.component.html',
  styleUrl: './category.component.scss'
})

export class CategoryComponent implements OnInit {
  public categorys: Array<Category>;

  constructor(public dialog: MatDialog, private serve: ConsumeServeService){
    this.categorys = [{id: 0, name: '', color: ''}];
  }

  ngOnInit(): void {
   this.getCategory();
  }

  getCategory(){
    this.serve.get("/category?nroPage=0&regXPage=30&order=id").subscribe(
      (res: any) => {
        console.log(res);
        this.categorys = res.data;
      },
      (err: any) => {
        console.log(err);
      }
    );
  }

  createCategoryDialog() {
    const dialogRef = this.dialog.open(DialogCategoryComponent);
    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      this.serve.post("/category", result).subscribe(
        (res: any) => {
          console.log(res);
          this.getCategory();
        },
        (err: any) => {
           console.log(err);
        }
      )
    });
  }

  deleteCategory(data: Category){
    this.serve.delete("/category", data.id).subscribe(
        (res: any) => {
          console.log(res);
          this.getCategory();
        },
        (err: any) => {
           console.log(err);
        }
      );
  }

} 
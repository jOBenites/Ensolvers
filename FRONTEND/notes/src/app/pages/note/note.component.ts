import { RouterModule } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatListModule } from '@angular/material/list';
import {MatDialog} from '@angular/material/dialog';
import {MatIconModule} from '@angular/material/icon';

import Note from '../../modules/note.model';
import { DialogNoteComponent } from '../../components/dialog-note/dialog-note.component';
import { ConsumeServeService } from '../../services/consume-serve.service';
import { MatButtonModule } from '@angular/material/button';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import {MatTooltipModule} from '@angular/material/tooltip';
import { DialogAsignLabelsComponent } from '../../components/dialog-asign-labels/dialog-asign-labels.component';
import {MatChipsModule} from '@angular/material/chips';

@Component({
  selector: 'app-note',
  standalone: true,
  imports: [
    RouterModule,
    MatCardModule,
    MatListModule,
    MatIconModule,
    MatButtonModule,
    MatFormFieldModule, 
    MatInputModule,
    MatTooltipModule,
    MatChipsModule,
    FormsModule
   ],
  templateUrl: './note.component.html',
  styleUrl: './note.component.scss'
})
export class NoteComponent implements OnInit {

  public notes: Array<Note> | undefined;
  public tempNotes: Array<Note> | undefined;

  constructor(public dialog: MatDialog, private serve: ConsumeServeService){
  }

  ngOnInit(): void {
    this.getNotes();
  }

  getNotes(){
    this.serve.get("/note?nroPage=0&regXPage=30&order=id").subscribe(
      (res: any) => {
        console.log(res);
        this.notes = res.data;
        this.tempNotes = this.notes;
      },
      (err: any) => {
        console.log(err);
      }
    );
  }

   createNoteDialog() {
    const dialogRef = this.dialog.open(DialogNoteComponent);
    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      if(result) {
        this.serve.post("/note", result).subscribe(
          (res: any) => {
            console.log(res);
            this.getNotes();
          },
          (err: any) => {
             console.log(err);
          }
        ); 
      }
    });
  }

  editNoteDialog(data: Note) {
    console.log(data);
    const dialogRef = this.dialog.open(DialogNoteComponent, {
      data: data
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      this.serve.put("/note", data.id, result).subscribe(
        (res: any) => {
          console.log(res);
          this.getNotes();
        },
        (err: any) => {
           console.log(err);
        }
      )
    });
  }

  deleteNote(data: Note){
    this.serve.delete("/note", data.id).subscribe(
        (res: any) => {
          console.log(res);
          this.getNotes();
        },
        (err: any) => {
           console.log(err);
        }
      );
  }

  archivedNote(data: Note){
    this.serve.putOther("/note/archived", data.id).subscribe(
        (res: any) => {
          console.log(res);
          this.getNotes();
        },
        (err: any) => {
           console.log(err);
        }
      );
  }

  labelNote(data: Note){
    const dialogRef = this.dialog.open(DialogAsignLabelsComponent, {
      data: data
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      this.serve.post(`/${data.id}/assign-categories`, result).subscribe(
        (res: any) => {
          console.log(res);
          this.getNotes();
        },
        (err: any) => {
           console.log(err);
        }
      )
    });
  }


  Category() {
    const dialogRef = this.dialog.open(DialogNoteComponent);
    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      this.serve.post("/category", result).subscribe(
        (res: any) => {
          console.log(res);
          this.getNotes();
        },
        (err: any) => {
           console.log(err);
        }
      )
    });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    if(filterValue != '') {
      let filtro = this.tempNotes!.filter(element => element.title.toLowerCase().includes(filterValue.toLowerCase()));
      
      let indice = 0;
      let flag = true;
      if(filtro.length <= 0) {
        while(flag) {
          let filtroCat = this.tempNotes![indice].categories!.filter(element => element.name.toLowerCase().includes(filterValue.toLowerCase()));
          if(filtroCat.length > 0) {
            flag = false;
          }
          indice++;
        }
        filtro = [this.tempNotes![indice -1]];
      }

      this.notes = filtro;
    } else {
      this.notes = this.tempNotes;
    }
  }

}

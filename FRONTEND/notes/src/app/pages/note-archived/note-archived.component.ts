
import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { MatListModule } from '@angular/material/list';
import {MatIconModule} from '@angular/material/icon';
import Note from '../../modules/note.model';
import { ConsumeServeService } from '../../services/consume-serve.service';
import { MatButtonModule } from '@angular/material/button';
import { MatTooltipModule } from '@angular/material/tooltip';

@Component({
  selector: 'app-note-archived',
  standalone: true,
   imports: [
    RouterModule,
    MatCardModule,
    MatListModule,
    MatIconModule,
    MatButtonModule,
    MatTooltipModule
   ],
  templateUrl: './note-archived.component.html',
  styleUrl: './note-archived.component.scss'
})
export class NoteArchivedComponent implements OnInit {
public notes: Array<Note> | undefined;

  constructor(private serve: ConsumeServeService){
    // this.notes = [{id: 0, title: '', description: '', categoryId: 0}];
  }

  ngOnInit(): void {
   this.getNotes();
  }

  getNotes(){
    this.serve.get("/listArchived?nroPage=0&regXPage=30&order=id").subscribe(
      (res: any) => {
        console.log(res);
        this.notes = res.data;
      },
      (err: any) => {
        console.log(err);
      }
    );
  }

  archivedNote(data: Note){
    this.serve.putOther("/note/dearchived", data.id).subscribe(
        (res: any) => {
          console.log(res);
          this.getNotes();
        },
        (err: any) => {
           console.log(err);
        }
      );
  }

}

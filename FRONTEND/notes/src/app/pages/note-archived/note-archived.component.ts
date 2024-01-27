import { HttpClient, HttpClientModule } from '@angular/common/http';
import { AfterViewInit, Component, OnInit } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatListModule } from '@angular/material/list';
import {MatIconModule} from '@angular/material/icon';
import Note from '../../modules/note.model';
import { ConsumeServeService } from '../../services/consume-serve.service';

@Component({
  selector: 'app-note-archived',
  standalone: true,
   imports: [
    HttpClientModule,
    MatCardModule,
    MatListModule,
    MatIconModule
   ],
  templateUrl: './note-archived.component.html',
  styleUrl: './note-archived.component.scss'
})
export class NoteArchivedComponent {
public notes: Array<Note>;

  constructor(private client: HttpClient,  private serve: ConsumeServeService){
    this.notes = [{id: 0, title: '', description: '', categoryId: 0}];
  }

  ngAfterViewInit(): void {
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

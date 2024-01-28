import { Routes } from '@angular/router';
import { NoteComponent } from './pages/note/note.component';
import { NoteArchivedComponent } from './pages/note-archived/note-archived.component';
import { CategoryComponent } from './pages/category/category.component';

/*export const routes: Routes = [
];*/

export const routes: Routes = [
  { path: 'notes', component: NoteComponent},
  { path: 'notes-archived', component: NoteArchivedComponent},
  { path: 'category', component: CategoryComponent},
  { path: '',   redirectTo: 'notes', pathMatch: 'full' },
  { path: '**', redirectTo: 'notes' },
];
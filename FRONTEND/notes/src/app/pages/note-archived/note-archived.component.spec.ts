import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NoteArchivedComponent } from './note-archived.component';

describe('NoteArchivedComponent', () => {
  let component: NoteArchivedComponent;
  let fixture: ComponentFixture<NoteArchivedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NoteArchivedComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(NoteArchivedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

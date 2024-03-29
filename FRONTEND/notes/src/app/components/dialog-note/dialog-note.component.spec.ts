import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogNoteComponent } from './dialog-note.component';

describe('DialogNoteComponent', () => {
  let component: DialogNoteComponent;
  let fixture: ComponentFixture<DialogNoteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DialogNoteComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DialogNoteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

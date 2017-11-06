/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic;

import dataaccess.NoteDB;
import domainmodel.Note;
import domainmodel.User;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class NoteService
{   
    public List<Note> getAll() throws Exception
    {
        return NoteDB.getAll();
    }
    
    public int delete(int noteID) throws Exception
    {
        Note deletedNote = NoteDB.getNote(noteID); 
        return NoteDB.delete(deletedNote);
    }
    
    public int insert(String title, String contents, User user) throws Exception {
        Note note = new Note(title,contents, user);
        return NoteDB.insert(note);
    }
    
}

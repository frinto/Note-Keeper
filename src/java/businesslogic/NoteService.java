/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic;

import dataaccess.DBException;
import dataaccess.NoteDB;
import domainmodel.Note;
import domainmodel.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class NoteService
{   
    public Note get(int noteId) throws Exception {
        return NoteDB.getNote(noteId);
    }
    
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

    public int update(Integer noteID, String title, String contents, User owner)
    {
        Note note = new Note(noteID,title,contents, owner);
        try
        {
            return NoteDB.update(note);
        } catch (DBException ex)
        {
            Logger.getLogger(NoteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
}

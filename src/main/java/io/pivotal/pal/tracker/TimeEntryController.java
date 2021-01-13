package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {
    private final TimeEntryRepository timeEntryRepository;
    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }
    @DeleteMapping("{timeEntryId}")
    public ResponseEntity<Void> delete(@PathVariable long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("{nonExistentTimeEntryId}")
    public ResponseEntity<TimeEntry> update(@PathVariable long nonExistentTimeEntryId, @RequestBody TimeEntry timeEntry) {

        TimeEntry timeEntry1 =timeEntryRepository.update(nonExistentTimeEntryId,timeEntry);
        if(timeEntry1!=null){
            return ResponseEntity.ok(timeEntry1);
        }else{
            return ResponseEntity.notFound().build();
        }

    }
    @GetMapping()
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> timeEntry = timeEntryRepository.list();
        return ResponseEntity.ok(timeEntry);
    }
    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id) {
        TimeEntry timeEntry =timeEntryRepository.find(id);
        if(timeEntry!=null){
            return ResponseEntity.ok(timeEntry);
        }else{
            return ResponseEntity.notFound().build();
        }

    }
    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {

        return ResponseEntity.created(null).body(timeEntryRepository.create(timeEntryToCreate));
    }
}

# points-of-interest


1.1 Περιγραφή και σχεδιάγραμμα των κλάσεων
package activities
      Σε αυτό το πακέτο περιλαμβάνονται τα .java αρχεία του κάθε activity που αφορούν
      το λειτουργικό κομμάτι της εφαρμογής.
            • Main.java : Στην κλάση αυτή ορίζονται και δημιουργούνται όλα τα αντικείμενα
            που φαίνονται στο αρχικό activity της εφαρμογής.
            • MoreInfo.java: Σε αυτό το activity φαίνονται οι περαιτέρω πληροφορίες-
            λεπτομέρειες για τα Pois που έχει επιλέξει ο χρήστης να εμφανιστούν. Αυτό αφορά
            και των δύο ειδών Poi που υπάρχουν στην τοπική βάση, δηλαδή Verified και
            Pending. Επίσης και από αυτό το activity ο χρήστης έχει την δυνατότητα να τα
            διαγράψει.
            • MyListFragment.java: Στην κλάση αυτή ορίζονται και δημιουργούνται όλα τα
            αντικείμενα που φαίνονται σε αυτό το activity. Σε αυτό το activity ο χρήστης μπορεί
            να δεί όλα του τα POIs(Verified και Pending) με τη μορφή λίστας, μπορεί να
            διαγράψει όποιο επιθυμεί αλλά και να επιλέξει όποιο θέλει κι από τα δύο είδη των
            Pois για να εμφανιστεί σε άλλο activity με περαιτέρω πληροφορίες.
            • SearchFragment.java: Στην κλάση αυτή ορίζονται και δημιουργούνται όλα τα
            αντικείμενα που φαίνονται σε αυτό το tab. Σε αυτό το tab o χρήστης μπορεί να
            επιλέξει έναν ή παραπάνω τύπους Pois και να κάνει αναζήτηση(μεταξύ των δικών του
            Pois, μεταξύ των άλλων Pois, μεταξύ όλων των Pois) Τα αποτελέσματα εμφανίζονται
            στην οθόνη, με τα αποτελέσματα που αφορούν Pois του ιδίου με διαφορετικό χρώμα
            καθώς και υπάρχει χάρτης που δείχνει που βρίσκονται αυτά τα Pois αλλά και που
            βρίσκεται ο χρήστης.
            • SignUp.java: Στην κλάση αυτή ορίζονται και δημιουργούνται όλα τα αντικείμενα
            που φαίνονται στο αρχικό activity της εφαρμογής.
            • TabsActivity.java: Στην κλάση αυτή ορίζονται και δημιουργούνται όλα τα
            αντικείμενα για την λειτουργία των tabs .Σε αυτή την κλάση δεν ορίζεται layout
            καθώς είναι η βάση για τη δημιουργία των τριών fragments.
Package asynctasks
      Σε αυτό το πακέτο γίνεται η επικοινωνία του client με τον server για όποια activities
      είναι απαραίτητο.
            
            •MyList_Delete_Task.java
            Σε αυτή τη κλάση γίνεται επικοινωνία του client με την συνάρτηση
            “deleteData” του server και ενημερώνεται ο χρήστης για την εξέλιξη
            της αίτησης του.•
            •MyTask_search_all.java
            Σε αυτή τη κλάση γίνεται επικοινωνία του client με την συνάρτηση
            “getMapData” του server καθώς και η αναζήτηση στη τοπική βάση
            για την εμφάνιση όλων των POIs . Ακόμα απεικονίζονται σε χάρτη τα
            αποτελέσματα αναζήτησης ( αν υπάρχουν ) .
            •MyTask_search_mine.java
            Σε αυτή τη κλάση γίνεται αναζήτηση στη τοπική βάση
            για την εμφάνιση όλων των POIs που έχει εισάγει ο χρήστης .Ακόμα
            απεικονίζονται σε χάρτη τα αποτελέσματα αναζήτησης αυτής ( αν υπάρχουν )
            .
            •MyTask_search_others.java
            Σε αυτή τη κλάση γίνεται επικοινωνία του client με την συνάρτηση “getMapData”
            του server για την εμφάνιση όλων των POIs που δεν έχει εισάγει ο χρήστης ( που
            δεν υπάρχουν στην τοπική βάση ).Ακόμα απεικονίζονται σε χάρτη τα
            αποτελέσματα αναζήτησης αυτής ( αν υπάρχουν ) .
            MyTask_set.java
            Σε αυτή τη κλάση γίνεται επικοινωνία του client με την συνάρτηση
            “setMonitorData” του server για την εισαγωγή νέου σημείου . Ακόμα σε
            κάθε νέα προσπάθεια εισαγωγής γίνεται αναζήτηση στον πίνακα pending
            ώστε να ικανοποιηθούν όσες αιτήσεις περιμένουν. Τέλος, ενημερώνεται ο
            χρήστης για την εξέλιξη
            της αίτησης του.
            Package database
 Στο πακέτο αυτό υλοποιείται η τοπική βάση της εφαρμογής με SQLite .
            •DataSource.java : Σε αυτή την κλάση δημιουργείται ένα αντικείμενο τύπου
            MySQLiteHelper(το οποίο δημιουργεί την βάση) και υπάρχουν όλες οι απαραίτητες
            συναρτήσεις για το άνοιγμα και το κλείσιμο της βάσης αλλά και για την εισαγωγή,
            διαγραφή και ανάκτηση των Pois (Verified and Pending) από την τοπική βάση.
            •MySQLiteHelper.java: Σε αυτή την κλάση δημιουργείται η τοπική βάση,
            δημιουργούνται οι πίνακες, τα πεδία των πινάκων και ορίζεται και η συνάρτηση
            ενημέρωσης της.
            •Pending.java: Εδώ ορίζονται τα πεδία και οι setters-getters για ένα
            αντικείμενου τύπου Pending, το οποίο αντικείμενο αντιπροσωπεύει τα Pois του
            χρήστη που δεν έχουν αποθηκευτεί (ακόμα) στην κεντρική βάση των χρηστών, στον
            server.
            •Verified.java: Εδώ ορίζονται τα πεδία και οι setters-getters για ένα
            αντικείμενου τύπου Verified , το οποίο αντικείμενο αντιπροσωπεύει τα Pois του
            χρήστη που έχουν αποθηκευτεί στην κεντρική βάση των χρηστών,στον server.
            Package gpsΣτην κλάση αυτή υλοποιούνται όλες οι απαραίτητες συναρτήσεις για την πλήρη
            λειτουργία της ανάκτησης τοποθεσίας.
            •AppLocationService.java: Σε αυτή την κλάση υλοποιείται η λειτουργία της
            ευρέσεως τοποθεσίας κάνοντας extend την κλάση Service(για να λειτουργεί
            background) και υλοποιώντας τον LocationListener(για να είναι ευαίσθητο σε
            αλλαγές τοποθεσίας).Στον constructor ενεργοποιείται η αναζήτηση περιοδικά και
            υπάρχουν συναρτήσεις για την ανάκτηση τοποθεσίας από τον πάροχο gps ή το δίκτυο
            αλλά και συναρτήσεις που ανακτούν την τοποθεσία όταν υπάρχει αισθητή μεταβολή
            στην τοποθεσία ή όταν αλλάξει ο πάροχος.
            
 Package listeners
 Στο πακέτο αυτό υλοποιούνται όλοι οι listeners του κάθε activity ξεχωριστά.
            • MainListeners.java
            Εδώ υπάρχουν οι listeners που υλοποιούν είτε τη δημιουργία νέου
            λογαριασμού , είτε την είσοδο στην εφαρμογή με ήδη
            καταχωρημένα
            στοιχεία.
            • MoreInfoListeners.java: Εδώ υπάρχουν οι listeners που υλοποιούν την διαγραφή
            του επιλεγμένου Verified η Pending Poi.
            • MyListFragmentListeners.java : Εδώ υπάρχει ο listener των στοιχείων της λίστας
            Verified(γιατί η λίστα που έχει χρησιμοποιηθεί υλοποιεί λίστα με δυνατότητα
            επιλογής ενός στοιχείου).Άρα, αυτός ο listener υλοποιεί την επιλογή-απεπιλογή του
            στοιχείου της λίστας από τον χρήστη και εμφανίζεται η αντίστοιχη επιλογή, γραφικά
            και στην οθόνη. Αντίστοιχα υπάρχει ο listener των στοιχείων λίστας Pending. Επίσης
            υπάρχουν οι listeners των δύο κουμπιών που έχει το αντίστοιχο activity, οι οποίοι
            υλοποιούν την εμφάνιση περαιτέρω πληροφοριών σε νέο activity ο ένας, και την
            διαγραφή των επιλεγμένων στοιχείων ο άλλος.
            • SearchFragmentListeners.java : Εδώ υλοποιούνται όλοι οι listeners των
            checkboxes που περιέχουν τους τύπους των Pois. Επίσης υπάρχουν οι listeners των 3
            κουμπιών αναζήτησης που υλοποιούνται με AsyncTask αλλά υλοποιείται και η
            συνάρτηση που επιστρέφει ποιοί τύποι επιλέχθηκαν από τον χρήστη για να
            χρησιμοποιηθούν στις αναζητήσεις.
            •SetFragmentListeners.java
            Εδώ υλοποιούνται όλοι οι listeners του SetFragment. Παίρνουν
            τιμές τα
            στοιχεία που έχουν επιλεγεί από το spinner καθώς και το όνομα του σημείου που
            έγραψε ο χρήστης. συντεταγμένες του
            νέου σημείου δίνονται το γεωγραφικό
            μήκος και πλάτος απο τη
            συσκευή, είτε είναι ανοιχτό το gps είτε όχι. Αν το
            όνομα που έδωσε
            χρήστης είναι έγκυρο τότε εκτελείται το AsyncTask.
            •SignUpListenersListeners.java
            Eδώ γίνεται η δημιουργία νέου λογαριασμού. Ακόμα γίνεται σύνδεση
            με
            τον server και πιο συγκεκριμένα με τη συνάρτηση registerUser. Στην περίπτωση του
            κουμπιού ακύρωσης ο χρήστης οδηγείται στη σελίδα εισόδου στην εφαρμογή ( log in
            ).Package inf.androidhive.tabsswipe.adapter
            Η κλάση αυτή υλοποιεί την λειτουργία των tabs της εφαρμογής.
            • TabsPagerAdapter.java
            Ορίζεται ένας adapter που μας επιτρέπει να μεταβούμε από το ένα tab στο άλλο.
            Παρακάτω φαίνεται το διάγραμμα των κλάσεων με τη χρήση του
            eclipse.
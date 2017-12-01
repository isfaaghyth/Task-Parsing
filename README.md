## Homework
example json parse with asyntask from mobile programming courses.

### json example
```json
{
   mahasiswa: [
      {
         nama: "Isfha",
         nim: "110215046",
         prodi: "TI"
      },
      {
         nama: "Dhimas",
         nim: "110115003",
         prodi: "SI"
      },
      {
         nama: "Ardi",
         nim: "110315031",
         prodi: "TK"
      }
   ]
}
```

with this;

#### database composition
```json
+-------+-------------+------+-----+---------+----------------+
| Field | Type        | Null | Key | Default | Extra          |
+-------+-------------+------+-----+---------+----------------+
| id    | int(11)     | NO   | PRI | NULL    | auto_increment |
| nama  | varchar(45) | YES  |     | NULL    |                |
| nim   | int(11)     | YES  |     | NULL    |                |
| prodi | varchar(2)  | YES  |     | NULL    |                |
+-------+-------------+------+-----+---------+----------------+
```

#### koneksi.php
```php
   define('SERPER', 'localhost');
   define('DB', 'praktikum_delapan');
   define('UNAME', 'root');
   define('PASS', '');
   $pdoDriver = "mysql:host=" . SERPER . ";dbname=" . DB;
   $dbConn = new PDO($pdoDriver, UNAME, PASS);
```

#### getdata.php
```php
   include_once 'koneksi.php';
   $kueriMahasiswa = "SELECT id,nama,nim,prodi FROM mahasiswa";
   $connMahasiswa = $dbConn->query($kueriMahasiswa);

   $results = [];
   $i = 0;

   foreach($connMahasiswa as $row) {
      $nama = $row['nama'];
      $nim = $row['nim'];
      $prodi = $row['prodi'];
      $results[$i] = [
         "nama" => $nama,
         "nim" => $nim,
         "prodi" => $prodi
      ];
      $i++;
   }

   echo json_encode(["mahasiswa" => $results]);
```

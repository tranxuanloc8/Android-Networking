<?php
$r = $_POST['chieurong'];
$d = $_POST['chieudai'];
$dt = $r * $d;
$cv = ($d + $r) * 2;
echo "Chu vi: " . $cv . "; Dien tich: " . $dt;
?>
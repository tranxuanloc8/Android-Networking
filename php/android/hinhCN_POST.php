<?php
    $dai = $_POST['chieudai'];
    $rong = $_POST['chieurong'];
    $dt = $rong*$dai;
    $cv = ($dai+$rong)*2;
    echo "Chu vi: ".$cv." | Dien tich: ".$dt;
?>
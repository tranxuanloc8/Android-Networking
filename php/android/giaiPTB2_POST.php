<?php
    $a = $_POST['a'];
    $b = $_POST['b'];
    $c = $_POST['c'];

    $delta = pow($b,2)-4*$a*$c;

    $result = "";
    if($a == 0){
      if($b == 0){
        $result = "Vô số nghiệm";
      }else{
        $x1 = -$c/$b;
        $result = "PT có 1 nghiệm duy nhất: " .$x1;
      }
    }else{
      if($delta < 0){
        $result = "PT vô số nghiệm";
      }else{
        if($delta == 0){
          $x1 = -$b/(2*$a);
          $result = "PT có nghiệm kép: ".$x1;
        }else{
          $x1 = ((-$b+sqrt($delta))/(2*$a));
          $x2 = ((-$b-sqrt($delta))/(2*$a));
          $result = "PT có 2 nghiệm phân biệt: x1 = ".$x1."; x2 = ".$x2;
        }
      }
    }
    echo "= " .$result;
    
?>
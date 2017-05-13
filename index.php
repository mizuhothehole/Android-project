<?php
if($_GET['dateFrom'] && $_GET['dateTo']){

$user = 'root';
$password = 'root';
$db = 'test';
$host = 'localhost';
$port = 8889;
$dateFrom = $_GET['dateFrom'];
$dateTo = $_GET['dateTo'];
$kind = $_GET['kind'];

$link = mysqli_init();
$success = mysqli_real_connect(
   $link, 
   $host, 
   $user, 
   $password, 
   $db,
   $port
);

    $mysqli = new mysqli($host, $user, $password, $db);
if($kind){
    $sql = "SELECT * FROM HOME_ACCOUNT WHERE ISSUE_DATE >= ".$dateFrom." AND ISSUE_DATE <= ".$dateTo." AND ISSUE_TITLE = '".$kind."'".";";
}else{
    $sql = "SELECT * FROM HOME_ACCOUNT WHERE ISSUE_DATE >= ".$dateFrom." AND ISSUE_DATE <= ".$dateTo.";";
}
    $result = $mysqli->query($sql);
    //$row = $result->fetch_assoc(); // 从结果集中取得一行作为关联数组
    echo "{datas:[";
    while ($row = mysqli_fetch_assoc($result)) {
        printf("{ID:%s,ISSUE_DATE:%s,ISSUE_TITLE:%s,ISSUE_AMOUNT:%s},", $row["ID"], $row["ISSUE_DATE"],$row["ISSUE_TITLE"],$row["ISSUE_AMOUNT"]);
    }   
echo"]}"; 
    $result->free();
    $mysql->close();
}
else{
echo "no GET parameter";
}
?>

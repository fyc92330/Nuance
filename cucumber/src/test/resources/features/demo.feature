Feature: 初始化Demo測試
  Scenario: Demo
    Given 把參數 1 2 3 4 5 加入集合
    Then 在流的狀態下參數變為兩倍
    And 過濾掉不是 3 的倍數
    Then 印出剩下的值
#!/usr/bin/env bash

# 쉬고 있는 profile을 찾는 부분: (ex. real1이 사용중이면 real2, real2가 사용중이면 real1)

function find_idle_profile()
{
  RESPONSE_CODE=$(curl -s -o /dev/null -w "%{http_code}" http://localhost/profile)

  if [ ${RESPONSE_CODE} -ge 400 ] # 400보다 크면, 즉 40x/50x 는 에러
  then
    CURRENT_PROFILE=real2
  else
    CURRENT_PROFILE=$(curl -s http://localhost/profile)
  fi

  if [ ${IDLE_PROFILE} == real ]
  then
    IDLE_PROFILE=real
  else
    if [ ${CURRENT_PROFILE} == real1 ]
    then
      IDLE_PROFILE=real2
    else
      IDLE_PROFILE=real1
    fi
  fi

  echo "${IDLE_PROFILE}"
}

function find_idle_port()
{
  IDLE_PROFILE=$(find_idle_profile)

if [ ${IDLE_PROFILE} == real ]
  then
    echo "8080"
  else
    if [ ${IDLE_PROFILE} == real1 ]
    then
      echo "8081"
    else
      echo "8082"
    fi
  fi



}
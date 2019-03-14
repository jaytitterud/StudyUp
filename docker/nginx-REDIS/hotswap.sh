#hotswap.sh
  
set -e
sed -i "s/server .*:6379/server $1:6379/" ../etc/nginx/nginx.conf
/usr/sbin/nginx -s reload 

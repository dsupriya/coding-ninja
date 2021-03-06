import java.util.*;

public class HelloWorld{

     public static void main(String []args){
        System.out.println("Hello World");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a[] = new int [n];
        for(int i=0;i<n;i++)
            a[i]=sc.nextInt();
        int tree[] = new int[2*n];
        sumTree(a,tree,1,0,n-1);
        //updateTree(a,tree,1,0,n-1,10,2);
       for(int i=0;i<2*n;i++)
        {System.out.print(tree[i]+" ");}
        System.out.println();
       int ans=query(tree,1,0,n-1,0,2);
        System.out.println("1 "+ans);
        ans=query(tree,1,0,n-1,1,1);
         System.out.println("2 "+ans);
    }
    
    public static void fillArray(int [] a)
    {
        int n = a.length;
        for(int i=0;i<n;i++)
            a[i]= -1*i;
        return;
    }
    public static void sumTree(int []a,int []tree, int node, int start, int end)
    {
        if(start==end)
        {
            tree[node] =  a[start];
            //System.out.println(a[start]  + " "+node);
            return;
        }
        int mid= (start+end)/2;
        sumTree(a,tree,node*2,start,mid);
        sumTree(a,tree,node*2+1,mid+1,end);
        tree[node] = tree[node*2]+tree[node*2+1];
        return;
        
        
    }
    
    public static void updateTree(int []a, int []tree, int node, int start, int end,  int value, int index)
    {
        
        if(start==end)
        {
            a[index]=value;
            tree[node]=value;
            return;
        }
        int mid =(start+end)/2;
        if(mid>index)
        {
            updateTree(a,tree,node*2,start,mid-1,value,index);
            
        }
        else
        {
            updateTree(a,tree,node*2+1,mid+1,end,value,index);
        }
        tree[node] = tree[node*2]+tree[node*2+1];
        return;
        
        
    }
    public static int query(int [] tree,int node, int start,int end, int left, int right)
    {
       //completely outside
       if(right<start || left >end)
        return 0;
        
        //complety within range
        if(start >=left &&  end <= right)
            return tree[node];
        
        else
        {
            int mid = (start + end)/2;
            int ans1 = query(tree,node*2,start,mid,left,right);
            int ans2= query(tree,node*2+1,mid+1,end,left,right);
            return ans1+ans2;
        }
        
    }
    
}
